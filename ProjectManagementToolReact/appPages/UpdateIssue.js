import React from 'react';
import { StyleSheet, ListView, Button, Text, TextInput, View} from 'react-native';

class UpdateIssue extends React.Component {

    constructor(props) {
        super(props);
        const { state } = this.props.navigation;
        this.state = { name: state.params.name, sprint: state.params.sprint,
                        previousName: state.params.name};
    }

    render() {
        return (
            <View style={styles.container}>
                <TextInput
                    style={{height: 40, width: 250, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(text) => this.setState({name:text})}
                    value={this.state.name}
                />
                <TextInput
                    style={{height: 40, width: 250, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(text) => this.setState({sprint:text})}
                    value={this.state.sprint}
                />
                <Button
                    onPress = {() => {
                        this.props.navigation.state.params.returnData(this.state.name, this.state.sprint,
                            this.state.previousName);
                        this.props.navigation.goBack();}
                    }
                    title="Update Issue"
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
        flexWrap: 'wrap',
        alignItems: 'center',
        flexDirection:'row',
        justifyContent: 'center',
    },
});

export default UpdateIssue;