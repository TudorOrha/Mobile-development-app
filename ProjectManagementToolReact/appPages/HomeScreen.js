import React from 'react';
import { StyleSheet, View, Button} from 'react-native';

class HomeScreen extends React.Component {
    constructor(props) {
        super(props);
        const {state} = this.props.navigation;
        this.state = {userType: state.params.userType}
        console.log(this.state.userType);
    }


    render() {
        const { navigate } = this.props.navigation;
        if (this.state.userType === "admin")return (
            <View style={styles.container}>
                <Button
                    onPress={() => navigate('AddIssue')}
                    title="Add Issues"
                    color="#841584"
                />
                <Button
                    onPress={() => navigate('ListIssues')}
                    title="List Issues"
                    color="#841584"
                />
            </View>
        );
		else return(
		    <View style={styles.container}>
                <Button
                    onPress={() => navigate('ListIssues')}
                    title="List Issues"
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

export default HomeScreen;