import React from 'react';
import {StackNavigator} from 'react-navigation';
import Login from './appPages/Login';
import HomeScreen from './appPages/HomeScreen';
import AddIssue from './appPages/AddIssue';
import UpdateIssue from './appPages/UpdateIssue'
import ListIssues from './appPages/ListIssues';
import * as firebase from 'firebase';


export default class App extends React.Component {
    constructor(props) {
        super(props);
        var config = {
            apiKey: "AIzaSyBFgi2q6LK00xUCfarmMH0VGDNU7WKr4IM",
            authDomain: "mobile-project-management-tool.firebaseapp.com",
            databaseURL: "https://mobile-project-management-tool.firebaseio.com",
            projectId: "mobile-project-management-tool",
            storageBucket: "mobile-project-management-tool.appspot.com",
            messagingSenderId: "809516781886"
        };
        firebase.initializeApp(config);
    }

    render() {
        return (
            <RootApp/>
        );
    }
}

const RootApp = StackNavigator({
    Login: {screen: Login,
        navigationOptions:{headerTitle: 'Login'}},
    Home: {screen: HomeScreen,
        navigationOptions: {headerTitle: 'Home'}},
    AddIssue: {screen: AddIssue,
        navigationOptions: {headerTitle: 'Add Issue'}},
    UpdateIssue: {screen: UpdateIssue,
        navigationOptions: {headerTitle: 'Update Issue'}},
    ListIssues: {screen: ListIssues,
        navigationOptions: {headerTitle: 'List Issues'}},
});

