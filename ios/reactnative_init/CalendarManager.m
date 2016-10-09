// CalendarManager.m
#import "CalendarManager.h"
#import "RCTConvert.h"
#import "RCTBridge.h"
#import "RCTEventDispatcher.h"

@implementation CalendarManager

@synthesize bridge=_bridge;

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(addEvent:(NSString *)name location:(NSString *)location)
{
  RCTLogInfo(@"Pretending to create an event %@ at %@", name, location);
}


RCT_REMAP_METHOD(findEventsPromise,
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject)
{
  NSArray *events =@[@"张三",@"李四",@"王五",@"赵六"];
  if (events) {
    resolve(events);
  } else {
    NSError *error=[NSError errorWithDomain:@"我是Promise回调错误信息..." code:101 userInfo:nil];
    reject(@"no_events", @"There were no events", error);
  }
}

@end