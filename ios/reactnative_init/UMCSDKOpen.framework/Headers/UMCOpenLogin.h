//
//  UMCOpenLogin.h
//  UMC
//
//  Created by LL on 16/5/30.
//  Copyright © 2016年 LL. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>

@interface UMCOpenLogin : NSObject

/**
 显式登录
 */
+ (void)loginExplicitly:(UIViewController *)vc complete:(void (^)(id sender))complete;
/**
 短信验证码登录
 */
+ (void)loginSMS:(UIViewController *)vc complete:(void (^)(id sender))complete;

/**
 获取用户资料
 */
+ (void)getUserInfo:(void (^)(id sender))complete;
/**
 获取用户状态
 */
+ (void)getUserState:(void (^)(id sender))complete;

/**
 校验手机号码
 */
+ (void)checkPhone:(NSString *)phone complete:(void (^)(id sender))complete;

@end
