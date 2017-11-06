import React from 'react';
import { StyleSheet, ListView, Button, Text} from 'react-native';
import UpdateIssue from './UpdateIssue';
import Issue from './Issue';

class ListIssues extends React.Component {

    constructor() {
        super();
        const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
        let issue1 = new Issue('Name1','Sprint1');
        let issue2 = new Issue('Name2','Sprint1');
        let issue3 = new Issue('Name3','Sprint2');
        this.issues = [issue1,issue2,issue3];
        this.state = {
            dataSource: ds.cloneWithRows(this.issues),
        };
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
        for (var i = 0; i < 3; i++) {
            if (this.issues[i].getName() === previousName) {
                this.issues[i].setName(name);
                this.issues[i].setSprint(sprint);
            }
        }
        this.setState({dataSource: ds.cloneWithRows(this.issues)})
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