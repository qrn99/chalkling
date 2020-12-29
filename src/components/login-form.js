import React from 'react';

import TextField from '@material-ui/core/TextField'
import Button from '@material-ui/core/Button'
import Paper from '@material-ui/core/Paper'
import Grid from '@material-ui/core/Grid'
import jQuery from 'jquery'

import '../styles/login.css'
import { SERVER_URL } from "./constants"

export default class LoginForm extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            username: '',
            password: '',
            isLogin: false,
            error: false
        }
    }

    fetchData = () => {
        const dataBody = JSON.stringify({
            username: this.state.username,
            password: this.state.password,
            isLogin: this.state.isLogin});
        console.log(dataBody);
        jQuery.ajax({
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            method: "POST",
            mode: "no-cors",
            url: SERVER_URL + "/api/login",
            dataType: 'json',
            data: dataBody
        })
    }

    updateLoginStatus = (event) => {
        event.preventDefault()
        this.fetchData()
        if (!this.state.isLogin) {
            this.setState({password: '', error: true})
        } else {
            this.setState({error: false})
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
                                           error={this.state.error}
                                           value={this.state.username}/>
                            </Grid>
                            <Grid item xs={12} id={'loginField'}>
                                <TextField id="standard-basic"
                                           label="Password"
                                           type="password"
                                           onChange={(event) => this.setState({password: event.target.value})}
                                           error={this.state.error}
                                           value={this.state.password}/>
                            </Grid>
                            <Grid item xs={12} id={'loginField'} align={'center'}>
                                <Button variant="contained" color="inherit" onClick={this.updateLoginStatus}>Login</Button>
                            </Grid>
                        </form>
                    </Paper>
                </Grid>
        );
    }
}