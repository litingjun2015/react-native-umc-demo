//
//  UMCOpenSDK.h
//  UMC
//
//  Created by LL on 16/6/13.
//  Copyright © 2016年 LL. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface UMCOpenSDK : NSObject

/**
 初始化参数
 @param clientID 申请能力平台成功后，分配给网站的appid
 @param clientSecret 申请能力平台成功后，分配给网站的appSecret
 @param redirect 申请能力平台时填写的地址
 */
+ (void)customInit:(NSString *)clientID clientSecret:(NSString *)clientSecret redirectURL:(NSString *)redirect;

/**
 自定义显示登录界面文本
 @param dict 自定义字典信息
 默认
 {
     loginTitle = @“登录统一认证”;
     historyTitle = @“登录统一认证”;
     smsTitle = @“登录统一认证”;
     loginVersion = @“”;
     historyVersion = @“”;
     smsVersion = @“”;
     showSMS = @“切换账号”;
 }
 */
+ (void)customUI:(NSDictionary *)dict;

/**
 清空登录账号
 */
+ (BOOL)cleanAllAccount;

@end
