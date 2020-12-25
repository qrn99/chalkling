import React from 'react';

import TextField from '@material-ui/core/TextField'
import Button from '@material-ui/core/Button'
import InputAdornment from "@material-ui/core/InputAdornment"
import { Visibility, VisibilityOff } from "@material-ui/icons"
import FormControl from "@material-ui/core/FormControl"
import InputLabel from "@material-ui/core/InputLabel"
import Input from "@material-ui/core/Input"
import IconButton from "@material-ui/core/IconButton"
import Paper from '@material-ui/core/Paper'
import Grid from '@material-ui/core/Grid'

import '../styles/login.css'
// import { SERVER_URL } from "./constants"

const LoginForm = () => {
  const [values, setValues] = React.useState({
    password: '',
    showPassword: false,
  });

  // TODO: Use for making requests.
  // const fetchData = (event) => {
  //     event.preventDefault()
  // }

  return(
      <div id={'loginForm'}>
          <Grid container direction="column" justify="center" alignItems="center">
              <Paper>
                <form noValidate autoComplete="off">
                    <Grid item xs={12} id={'loginField'}>
                        <TextField id="standard-basic" label="Username" />
                    </Grid>
                    <Grid item xs={12} id={'loginField'}>
                      <FormControl>
                        <InputLabel htmlFor="standard-adornment-password">Password</InputLabel>
                        <Input
                          id="standard-adornment-password"
                          type={values.showPassword ? 'text' : 'password'}
                          onChange={(event) => setValues({ ...values, ['password']: event.target.value })}
                          endAdornment={
                            <InputAdornment position="end">
                              <IconButton
                                aria-label="toggle password visibility"
                                onClick={() => setValues({ ...values, showPassword: !values.showPassword })}
                                onMouseDown={(event) => event.preventDefault()}
                              >
                                {values.showPassword ? <Visibility /> : <VisibilityOff />}
                              </IconButton>
                            </InputAdornment>
                          }
                        />
                      </FormControl>
                    </Grid>
                    <Grid item xs={12} id={'loginField'} align={'center'}>
                      <Button variant="contained" color="inherit">Login</Button>
                    </Grid>
                </form>
              </Paper>
          </Grid>
      </div>
  );
}

export default LoginForm;