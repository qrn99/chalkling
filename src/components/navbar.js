import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  button: {
    marginRight: theme.spacing(4),
  },
  appBar: {
    background: 'transparent',
    boxShadow: 'none'
  },
}));

export default function Navbar() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <AppBar position="static" className={classes.appBar}>
        <Toolbar>
          <Button color="inherit" href={"/"} className={classes.button}>Home</Button>
          <Button color="inherit" href={"/blog"} className={classes.button}>Blog</Button>
        </Toolbar>
      </AppBar>
    </div>
  );
}
