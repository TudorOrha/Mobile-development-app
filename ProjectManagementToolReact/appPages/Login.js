import React from 'react';
import { StyleSheet, View, Button, TextInput} from 'react-native';
import * as firebase from 'firebase';

class Login extends React.Component {
    constructor() {
        super();
        console.log();
        this.state = {
            email: 'a@yahoo.com',
            password: '123456',
            admins: []
        }
    }

    componentWillMount(){
        let data = firebase.database().ref("/admins");
        data.on('value', (admins) => {
            let emails = Object.values(admins.val());
            this.setState({admins: emails});
        });
    }

    checkUserType(email) {
        const { navigate } = this.props.navigation;
        let notAdmin = 1;
        this.state.admins.map((adminEmail) => {
            if (adminEmail == email){
                navigate('Home',{userType: "admin"});
                notAdmin = 0;
            }
        });
        if (notAdmin === 1) navigate('Home',{userType: "normal"});
    }

    loginPressed() {
        this.setState({ error: ''});

        firebase.auth().signInWithEmailAndPassword(this.state.email, this.state.password)
            .then(() => {
                this.checkUserType(this.state.email);
                console.log(this.state.email, " signed in.");

            })
            .catch(() => { // Creating new account, if login was not successful. Password must be at least 6 chars.
                firebase.auth().createUserWithEmailAndPassword(this.state.email, this.state.password)
                    .then(() => {
                        this.setState({ error: ''});
                        console.log(this.state.email, " user created.");
                    })
                    .catch(() => {
                        this.setState({ error: 'Authentication failed.'});
                        console.log("error:", this.state.error);
                    });
            });
    }

    render() {
        return (
            <View style={styles.container}>
                <TextInput
                    style={{height: 40, width: 200, borderColor: 'gray', borderWidth: 1}}
                    placeholder='Email'
                    value={this.state.email}
                    onChangeText={(text) => this.setState({email:text})}
                />
                <TextInput
                    style={{height: 40, width: 200, borderColor: 'gray', borderWidth: 1}}
                    placeholder='Password'
                    value={this.state.password}
                    secureTextEntry
                    onChangeText={(text) => this.setState({password:text})}
                />
                <Button
                    onPress={() => this.loginPressed()}
                    title="Login"
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

export default Login;