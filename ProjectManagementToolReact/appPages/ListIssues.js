import React from 'react';
import { StyleSheet, ListView, Button, Text} from 'react-native';
import UpdateIssue from './UpdateIssue';
import Issue from './Issue';
import * as firebase from 'firebase';

class ListIssues extends React.Component {

    constructor() {
        super();
        const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
        this.issues = [];
        this.state = {
            dataSource: ds.cloneWithRows(this.issues),
        };
    }

    componentWillMount() {
        const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
        let data = firebase.database().ref("/issues");
        data.on('value', (issues) => {
            let issuesObjs = Object.values(issues.val());
            issuesObjs.map((issue) => {
                this.issues.push(new Issue(issue.name, issue.sprint));
            });
            this.setState({dataSource: ds.cloneWithRows(this.issues)});
        });
    }

    render() {
        const { navigate } = this.props.navigation;
        return (
            <ListView
                dataSource={this.state.dataSource}
                renderRow={(rowData) =>
                    <Text onPress={() => navigate('UpdateIssue',{ name: rowData.getName(),sprint: rowData.getSprint(),
                        returnData: this.returnData.bind(this) })}>{rowData.getName()}</Text>
                }
            />
        );
    }

    returnData(name, sprint, previousName) {
        const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
        let data = firebase.database().ref("/issues");

        for (var i = 0; i < this.issues.length; i++) {
            if (this.issues[i].getName() === previousName) {
                this.issues[i].setName(name);
                this.issues[i].setSprint(sprint);
            }
        }
        data.set(this.issues);
        this.issues = this.issues.splice(0,this.issues.length / 2);
        this.setState({dataSource: ds.cloneWithRows(this.issues)});


    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        flexWrap: 'wrap',
        alignItems: 'center',
        flexDirection:'row',
        justifyContent: 'center',
    },
});

export default ListIssues;