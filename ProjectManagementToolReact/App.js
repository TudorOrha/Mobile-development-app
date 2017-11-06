import React from 'react';
import {StackNavigator} from 'react-navigation';
import HomeScreen from './appPages/HomeScreen';
import AddIssue from './appPages/AddIssue';
import UpdateIssue from './appPages/UpdateIssue'
import ListIssues from './appPages/ListIssues';

const App = StackNavigator({
    Home: {screen: HomeScreen,
        navigationOptions: {headerTitle: 'Home'}},
    AddIssue: {screen: AddIssue,
        navigationOptions: {headerTitle: 'Add Issue'}},
    UpdateIssue: {screen: UpdateIssue,
        navigationOptions: {headerTitle: 'Update Issue'}},
    ListIssues: {screen: ListIssues,
        navigationOptions: {headerTitle: 'List Issues'}},
});

export default App;

