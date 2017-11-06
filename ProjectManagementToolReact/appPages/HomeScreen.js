import React from 'react';
import { StyleSheet, View, Button} from 'react-native';

class HomeScreen extends React.Component {
    render() {
        const { navigate } = this.props.navigation;
        return (
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