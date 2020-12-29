import React from 'react';

import TextField from '@material-ui/core/TextField'
import Button from '@material-ui/core/Button'
import Paper from '@material-ui/core/Paper'
import Grid from '@material-ui/core/Grid'
import jQuery from 'jquery'

import '../styles/login.css'
import { getServerURL } from "./constants"

const fetch = require("node-fetch");

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

    fetchData = (event) => {
        event.preventDefault()
        const dataBody = JSON.stringify({
            username: this.state.username,
            password: this.state.password,
            isLogin: this.state.isLogin});
        fetch(getServerURL() + "/api/login",
            {
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                },
                method: "POST",
                body: dataBody,
            })
            .then(dataJson => dataJson.json()).then(data => {
                // check if it logs in or not.
                this.setState({isLogin: Boolean(data.isLogin)})
                this.updateErrorStatus()
        }).catch(err => {
            if (err === "server") return
        })

    }

    updateErrorStatus = () => {
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
                                <Button variant="contained" color="inherit" onClick={this.fetchData}>Login</Button>
                            </Grid>
                        </form>
                    </Paper>
                </Grid>
        );
    }
}