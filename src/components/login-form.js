import React from 'react';

import TextField from '@material-ui/core/TextField'
import Button from '@material-ui/core/Button'
import FormControl from "@material-ui/core/FormControl"
import InputLabel from "@material-ui/core/InputLabel"
import Input from "@material-ui/core/Input"
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
            success: true,
        }
    }
    clear = () => {
        this.setState({password: ''})
    }

    // TODO: Use for making requests.
    fetchData = (event) => {
        event.preventDefault()

        // for now it just alternates
        this.setState({success: !this.state.success})
        console.log(this.state.password)
        this.clear()
    }

    render() {
        return (
                <Grid container direction="column" justify="center" alignItems="center" >
                    <Paper id={'loginForm'}>
                        <form noValidate autoComplete="off">
                            <Grid item xs={12} id={'loginField'}>
                                <TextField error={!this.state.success}
                                           id="standard-basic"
                                           label="Username"
                                           onChange={(event) => this.setState({success: event.target.value, username: event.target.value})}
                                           value={this.state.username}/>
                            </Grid>
                            <Grid item xs={12} id={'loginField'}>
                                <FormControl error={!this.state.success}
                                             onChange={(event) => this.setState({success: event.target.value})}>
                                    <InputLabel htmlFor="standard-adornment-password">Password</InputLabel>
                                    <Input
                                        id="standard-adornment-password"
                                        type={'password'}
                                        onChange={(event) => this.setState({password: event.target.value})}
                                        value={this.state.password}
                                    />
                                </FormControl>
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