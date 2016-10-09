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
  TouchableHighlight
} from 'react-native';

import ToastAndroid from './ToastAndroid';
import UMCAndroid from './UMCAndroid';

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

// https://github.com/facebook/react-native/blob/master/Examples/Movies/SearchScreen.js
  fetchSignup() {

     ToastAndroid.show('Awesome native call from method', ToastAndroid.SHORT);


//     this.timeoutID = null;
//
//     this.setState({filter: query});

//     var cachedResultsForQuery = resultsCache.dataForQuery[query];
//     if (cachedResultsForQuery) {
//       if (!LOADING[query]) {
//         this.setState({
//           dataSource: this.getDataSource(cachedResultsForQuery),
//           isLoading: false
//         });
//       } else {
//         this.setState({isLoading: true});
//       }
//       return;
//     }

//     LOADING[query] = true;
//     resultsCache.dataForQuery[query] = null;
//     this.setState({
//       isLoading: true,
//       queryNumber: this.state.queryNumber + 1,
//       isLoadingTail: false,
//     });


//     fetch('http://beta.duangwifi.cn/umc/register/reg', {
//       method: 'POST',
//       headers: {
//         'Accept': 'application/json',
//         'Content-Type': 'application/json',
//       },
//       body: JSON.stringify({
//         loginAccount: '13264760029'
//       })
//     })

let formData = new FormData();
formData.append('loginAccount', '13264760029');

//    let options = {};
//    options.headers['Content-Type'] = 'multipart/form-data';
//    options.body = formData;

    console.log('litingjun fetching');

     fetch('http://7f2e253c.ngrok.io/yii/my/duangplat/publicplat/web/umc/register/reg', {
                 method: 'POST',
                 headers: {
                   'Accept': 'application/json',
                   'Content-Type': 'multipart/form-data',
                 },
                 body: formData
               })
       .then((response) => response.json())
       .then((responseData) => {
         ToastAndroid.show('ok', ToastAndroid.SHORT);
         ToastAndroid.show(responseData.username, ToastAndroid.SHORT);
         ToastAndroid.show(responseData.telephone, ToastAndroid.SHORT);

//         LOADING[query] = false;
//         resultsCache.totalForQuery[query] = responseData.total;
//         resultsCache.dataForQuery[query] = responseData.movies;
//         resultsCache.nextPageNumberForQuery[query] = 2;
//
//         if (this.state.filter !== query) {
//           // do not update state if the query is stale
//           return;
//         }
//
//         this.setState({
//           isLoading: false,
//           dataSource: this.getDataSource(responseData.movies),
//         });
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
          To get started, edit index.android.js test native module call
        </Text>

        <Text style={styles.instructions}>
        </Text>


        <CustomButton text="toast"
                    onPress={()=> {ToastAndroid.show('Awesome native call', ToastAndroid.SHORT);
                    console.log("ToastAndroid.show");
                  }}
        />

        <Text style={styles.instructions}>
        </Text>

        <CustomButton text="中移动统一认证显式登录"
                    onPress={()=> UMCAndroid.show('Awesome native call', ToastAndroid.SHORT)}
                    
        />

        <CustomButton
                  text="点击跳转到Activity界面,并且等待数据返回..."
                  onPress={()=>UMCAndroid.startActivityFromJSGetResult("ThreeActivity",200,(msg) => {
                            ToastAndroid.show('JS界面:从Activity中传输过来的数据为:'+msg,ToastAndroid.SHORT);
                          },
                           (result) => {
                            ToastAndroid.show('JS界面:错误信息为:'+result,ToastAndroid.SHORT);
                          })}
                />


        <Text style={styles.instructions}>

                </Text>

        <Text style={styles.instructions}>
        </Text>


        <CustomButton text="fetch"
                    onPress={()=> this.fetchSignup() }
        />

        <Text style={styles.instructions}>
        </Text>


        <Text style={styles.instructions}>
          Double tap R on your keyboard to reload,{'\n'}
          Shake or press menu button for dev menu
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
