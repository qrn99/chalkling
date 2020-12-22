import React from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';
import '../styles/navbar.css'

export default function Navbar() {

  return (
    <div>
      <AppBar position="static" color="transparent" elevation={0}>
        <Toolbar>
          <Button color="inherit" href={"/"} id={'button'}>Home</Button>
          <Button color="inherit" href={"/blog"} id={'button'}>Blog</Button>
        </Toolbar>
      </AppBar>
    </div>
  );
}
