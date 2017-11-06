import React from 'react';
import { StyleSheet, View, Button, Text, TextInput} from 'react-native';
import { email } from 'react-native-communications';

class AddIssue extends React.Component {
    constructor(props) {
        super(props);
        this.state = { name: 'Name', sprint: 'Sprint' };
    }

    render() {
        return (
            <View style={styles.container}>
                <Text>
                    This is the AddIssues Page.
                </Text>
                <TextInput
                    style={{height: 40, width: 200, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(text) => this.setState({name:text})}
                    value={this.state.name}
                />
                <TextInput
                    style={{height: 40, width: 200, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(text) => this.setState({sprint:text})}
                    value={this.state.sprint}
                />
                <Button
                    onPress = {() => {email(['tudor_orha@yahoo.com'], [], [], 'The Following issue has been added',
                        'Name: ' + this.state.name + '\nSprint: ' + this.state.sprint)}}
                    title="Add Issue"
                    color="#841584"
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
});

export default AddIssue;