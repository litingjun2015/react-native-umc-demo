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
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit index.ios.js native module call
        </Text>
        <Text style={styles.instructions}>

                </Text>
         <CustomButton text="button"
                            onPress={()=> CalendarManager.addEvent('Birthday Party', '4 Privet Drive, Surrey') }
                />

        <Text style={styles.instructions}>

        </Text>

        <CustomButton text="中移动统一认证显式登录"
            onPress={()=>CMpassportManager.addEvent('生日聚会', '江苏南通 中天路')}
        />

        <Text style={styles.instructions}>

                </Text>

        <Text style={styles.instructions}>
          Press Cmd+R to reload,{'\n'}
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
