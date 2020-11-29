import React from 'react';

import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import InputAdornment from "@material-ui/core/InputAdornment"
import { Visibility, VisibilityOff } from "@material-ui/icons"
import FormControl from "@material-ui/core/FormControl"
import InputLabel from "@material-ui/core/InputLabel"
import Input from "@material-ui/core/Input"
import IconButton from "@material-ui/core/IconButton"

// https://material-ui.com/components/text-fields/

const LoginFields = () => {
  const [values, setValues] = React.useState({
    password: '',
    showPassword: false,
  });

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  };

  const handleClickShowPassword = () => {
    setValues({ ...values, showPassword: !values.showPassword });
  };

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  return(
    <form noValidate autoComplete="off">
      <TextField id="standard-basic" label="Username" />
      <FormControl>
        <InputLabel htmlFor="standard-adornment-password">Password</InputLabel>
        <Input
          id="standard-adornment-password"
          type={values.showPassword ? 'text' : 'password'}
          onChange={handleChange('password')}
          endAdornment={
            <InputAdornment position="end">
              <IconButton
                aria-label="toggle password visibility"
                onClick={handleClickShowPassword}
                onMouseDown={handleMouseDownPassword}
              >
                {values.showPassword ? <Visibility /> : <VisibilityOff />}
              </IconButton>
            </InputAdornment>
          }
        />
      </FormControl>
      <Button variant="contained" color="secondary">Login!</Button>
    </form>
  );
}

export default LoginFields;