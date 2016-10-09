//
//  CalendarManager.m
//  ModulesDemo
//
//  Created by 江清清 on 16/5/22.
//  Copyright © 2016年 Facebook. All rights reserved.
//

#import "CMpassportManager.h"
#import "RCTConvert.h"
#import "RCTBridge.h"
#import "RCTEventDispatcher.h"

#import <UMCSDKOpen/UMCSDKOpen.h>

#import "UIView+React.h"

//普通权限
#define APP_ID_L                        @"300009228352"
#define APP_KEY_L                       @"440591DD9C69F864A2645F7217CC4590"
//高级权限
#define APP_ID_H                        @"300008925958"
#define APP_KEY_H                       @"CE11C24299BAF37B7A0FF226104ED3B1"


@implementation CMpassportManager

@synthesize bridge=_bridge;

//默认名称

RCT_EXPORT_MODULE()



//对外提供调用方法
RCT_EXPORT_METHOD(addEvent:(NSString *)name location:(NSString *)location){
  NSLog(@"CMpassport Pretending to create an event %@ at %@", name, location);
  
  NSLog(@"click");
  
  UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;
  
  [UMCOpenLogin loginExplicitly:rootViewController complete:^(id sender) {
    NSLog(@"=open=显式登录:%@",sender);
    dispatch_async(dispatch_get_main_queue(), ^{
      
//      NSLog();
//      NSString *message = [self changeShowMessage:sender];
//      UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"提示"
//                                                          message:[NSString stringWithFormat:@"%@",message]
//                                                         delegate:nil
//                                                cancelButtonTitle:@"确定"
//                                                otherButtonTitles:nil, nil];
//      [alertView show];
      
    });
  }];


  NSLog(@"click end");
  
}

//RCT_REMAP_METHOD(findEventsPromise,
//                 resolver:(RCTPromiseResolveBlock)resolve
//                 rejecter:(RCTPromiseRejectBlock)reject)
//{
//  NSArray *events =@[@"张三",@"李四",@"王五",@"赵六"];
//  if (events) {
//    resolve(events);
//  } else {
//    NSError *error=[NSError errorWithDomain:@"我是Promise回调错误信息..." code:101 userInfo:nil];
//    reject(@"no_events", @"There were no events", error);
//  }
//}


#pragma mark - Show
/**
 转换成显示的信息
 */
- (NSString *)changeShowMessage:(id)sender {
  NSString *message = @"";
  id resultcode = sender[@"resultcode"];
  if ([resultcode isEqual:@"000"]) {
    //
    id desc = sender[@"desc"];;
    message = [message stringByAppendingFormat:@"\n描述:%@",desc];
    //
    id passid = sender[@"passid"];;
    message = [message stringByAppendingFormat:@"\n通行证号:%@",passid];
    //
    id uniqueid = sender[@"uniqueid"];;
    message = [message stringByAppendingFormat:@"\n用户身份标识:%@",uniqueid];
    //
    id accesstoken = sender[@"accesstoken"];;
    message = [message stringByAppendingFormat:@"\naccesstoken:%@",accesstoken];
  }else {
    //
    message = [message stringByAppendingFormat:@"\n错误码:%@",resultcode];
    //
    id desc = sender[@"desc"];;
    message = [message stringByAppendingFormat:@"\n错误描述:%@",desc];
  }
  return message;
}

RCT_REMAP_METHOD(loginExplicitly,
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
  
  NSLog(@"click");
  
  [UMCOpenSDK customInit:APP_ID_H clientSecret:APP_KEY_H redirectURL:nil];
  
  
  
  NSLog(@"begin显式登录showExplicitLogin");
  
  UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;

  
  [UMCOpenLogin loginExplicitly:rootViewController complete:^(id sender) {
    NSLog(@"=open=显式登录:%@",sender);
    dispatch_async(dispatch_get_main_queue(), ^{
      NSString *message = [self changeShowMessage:sender];
      UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"提示"
                                                          message:[NSString stringWithFormat:@"%@",message]
                                                         delegate:nil
                                                cancelButtonTitle:@"确定"
                                                otherButtonTitles:nil, nil];
      [alertView show];
      
      
      [UMCOpenLogin getUserInfo:^(id sender) {
        NSLog(@"=open=用户资料:%@",sender);
        
        dispatch_async(dispatch_get_main_queue(), ^{
          UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"提示"
                                                              message:[NSString stringWithFormat:@"%@",sender]
                                                             delegate:nil
                                                    cancelButtonTitle:@"确定"
                                                    otherButtonTitles:nil, nil];
          [alertView show];
          
          
                    if (sender) {
                      resolve([NSString stringWithFormat:@"%@",sender]);
                    } else {
                      NSError *error=[NSError errorWithDomain:@"我是Promise回调错误信息..." code:101 userInfo:nil];
                      reject(@"no_events", @"There were no events", error);
                    }
          
          
        });
      }];
      
      
      
      
    });
  }];

  
  
  NSLog(@"click end");
  
}


// RCT_EXPORT_METHOD(
//   sometingToDoWithTheViewControllerOfTheComponent:(nonnull NSNumber *)reactTag // Component 对象的 reactTag
//   resolver:(RCTPromiseResolveBlock)resolve // 这行 
//   rejecter:(RCTPromiseRejectBlock)reject   // 和这行是可选的，如果需要在执行完毕后给 JavaScript 通知的话，就带上
// )
// {
//   // 实现后文详述
// }



@end
