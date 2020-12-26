import React from 'react';

import TextField from '@material-ui/core/TextField'
import Button from '@material-ui/core/Button'
import Paper from '@material-ui/core/Paper'
import Grid from '@material-ui/core/Grid'

import '../styles/login.css'
// import { SERVER_URL } from "./constants"

export default class LoginForm extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            username: '',
            password: '',
            isLogin: false,
        }
    }
    // TODO: Use for making requests.
    fetchData = (event) => {
        event.preventDefault()

        // for now it just alternates
        this.setState({isLogin: !this.state.isLogin})

        // clear password if it fails
        if (!this.state.isLogin) {
            this.setState({password: ''})
        }
    }

    render() {
        return (
                <Grid container direction="column" justify="center" alignItems="center" >
                    <Paper id={'loginForm'}>
                        <form noValidate autoComplete="off">
                            <Grid item xs={12} id={'loginField'}>
                                <TextField id="standard-basic"
                                           label="Username"
                                           onChange={(event) => this.setState({username: event.target.value})}
                                           error={this.state.isLogin}
                                           value={this.state.username}/>
                            </Grid>
                            <Grid item xs={12} id={'loginField'}>
                                <TextField id="standard-basic"
                                           label="Password"
                                           type="password"
                                           onChange={(event) => this.setState({password: event.target.value})}
                                           error={this.state.isLogin}
                                           value={this.state.password}/>
                            </Grid>
                            <Grid item xs={12} id={'loginField'} align={'center'}>
                                <Button variant="contained" color="inherit" onClick={this.fetchData}>Login</Button>
                            </Grid>
                        </form>
                    </Paper>
                </Grid>
        );
    }
}