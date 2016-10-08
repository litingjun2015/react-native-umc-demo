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

// RCT_EXPORT_METHOD(
//   sometingToDoWithTheViewControllerOfTheComponent:(nonnull NSNumber *)reactTag // Component 对象的 reactTag
//   resolver:(RCTPromiseResolveBlock)resolve // 这行 
//   rejecter:(RCTPromiseRejectBlock)reject   // 和这行是可选的，如果需要在执行完毕后给 JavaScript 通知的话，就带上
// )
// {
//   // 实现后文详述
// }



@end
