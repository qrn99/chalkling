import React from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';
import GitHubIcon from '@material-ui/icons/GitHub';
import IconButton from '@material-ui/core/IconButton';

import '../styles/navbar.css'

export default function Navbar() {

  return (
    <div>
      <AppBar position="static" color="transparent" elevation={0}>
        <Toolbar>
          <Button color="inherit" size={'large'} href={"/"} id={'buttonLeft'}>Home</Button>
          <Button color="inherit" size={'large'} href={"/blog"} id={'buttonLeft'}>Blog</Button>
          <div id={'icons'}>
            <IconButton color="inherit" size={'large'} id={'buttonRight'} href={'https://github.com/jangarong/chalkling'}><GitHubIcon /></IconButton>
          </div>
        </Toolbar>
      </AppBar>
    </div>
  );
}
