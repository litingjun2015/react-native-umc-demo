/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  TouchableHighlight,
  Image
} from 'react-native';

import { NativeModules } from 'react-native';
var CalendarManager = NativeModules.CalendarManager;
var CMpassportManager = NativeModules.CMpassportManager;


class CustomButton extends React.Component {
  render() {
    return (
      <TouchableHighlight
        style={styles.button}
        underlayColor="#a5a5a5"
        onPress={this.props.onPress}>
        <Text style={styles.buttonText}>{this.props.text}</Text>
      </TouchableHighlight>
    );
  }
}


class reactnative_init extends Component {

    constructor(props){
        super(props);
        this.state={
            events:'',
            notice:'',
        }
      }


  //获取Promise对象处理
    async _updateEvents(){
      try{
          var events=await CalendarManager.findEventsPromise();
          this.setState({events});



      }catch(e){
          console.error(e);
      }
    }

    async _loginupdateEvents(){
          try{
              var events=await CMpassportManager.loginExplicitly();
              this.setState({events});

              //console.log(events);
              this.fetchSignup(events);

          }catch(e){
              console.error(e);
          }
        }

renderImage(imgURI) {
    return (
      <Image source={{uri: imgURI}} />
    );
  }

  fetchSignup(info) {



      //    let options = {};
      //    options.headers['Content-Type'] = 'multipart/form-data';
      //    options.body = formData;

       console.log('litingjun fetching');


       //var info = '{ andid = \"\"; email = \"\"; msisdn = 18314330004; nickName = \"\"; }';
       //console.log(info);
       var begin = info.indexOf("msisdn");
       //console.log(begin);
       var end = info.indexOf(";", begin);
       //console.log(end);
       if(begin > 0 && end > 0)
       {
            var telephone = info.substring(begin+9, end);
            console.log(telephone);
       }
       else
       {
        //todo
        console.error('check format');
        return;
       }

       let formData = new FormData();
       formData.append('loginAccount', telephone);

       fetch('http://beta.duangwifi.cn/umc/register/reg', {
                   method: 'POST',
                   headers: {
                     'Accept': 'application/json',
                     'Content-Type': 'multipart/form-data',
                   },
                   body: formData
                 })
         .then((response) => response.json())
         .then((responseData) => {

             console.log(responseData.username);
             console.log(responseData.telephone);

             var events = responseData.username + ' ' + responseData.telephone;
             this.setState({events});

         }).catch((error) => {
                   console.error(error);
         })
         .done();

    }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit index.ios.js native module call, 1008
        </Text>
        <Text style={styles.instructions}>

                </Text>
         <CustomButton text="button"
                            onPress={()=> CalendarManager.addEvent('Birthday Party', '4 Privet Drive, Surrey') }
                />

        <Text style={styles.instructions}>
        </Text>

        <Text style={{marginLeft:5}}>
          'Callback的返回数据为:'+{this.state.events}
        </Text>
        <CustomButton text="点击调用原生模块findEventsPromise方法-Promise"
            onPress={ ()=>this._updateEvents()
            }
        />

        <Text style={styles.instructions}>
                </Text>



        <CustomButton text="中移动统一认证显式登录"
            onPress={()=>CMpassportManager.addEvent('生日聚会', '江苏南通 中天路')}
        />

         <Text style={styles.instructions}>
                        </Text>


        <CustomButton text="中移动统一认证显式登录"
                    onPress={()=>this._loginupdateEvents() }
                />

        <Text style={styles.instructions}>

                </Text>


                <CustomButton text="fetch"
                                    onPress={()=> this.fetchSignup() }
                        />


        <Text style={styles.instructions}>
          i Press Cmd+R to reload,{'\n'}
          Cmd+D or shake for dev menu
        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('reactnative_init', () => reactnative_init);
