import React from "react"
import Drawer from '@material-ui/core/Drawer';
import Button from '@material-ui/core/Button';
import List from '@material-ui/core/List';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import MessageIcon from '@material-ui/icons/Message';
import ListItemText from '@material-ui/core/ListItemText';
import ListItem from '@material-ui/core/ListItem';
import { makeStyles } from '@material-ui/core/styles';
import clsx from "clsx";
import CssBaseline from "@material-ui/core/CssBaseline"

// source: https://material-ui.com/components/drawers/

const drawerWidth = 250;

const useStyles = makeStyles((theme) =>({
  root: {
    display: 'flex',
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
  },
  // drawerHeader: {
  //   display: 'flex',
  //   alignItems: 'center',
  //   padding: theme.spacing(0, 1),
  //   // necessary for content to be below app bar
  //   ...theme.mixins.toolbar,
  //   justifyContent: 'flex-end',
  // },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3),
    transition: theme.transitions.create('margin', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    marginLeft: -drawerWidth,
  },
  contentShift: {
    transition: theme.transitions.create('margin', {
      easing: theme.transitions.easing.easeOut,
      duration: theme.transitions.duration.enteringScreen,
    }),
    marginLeft: 0,
  },
  drawerPaper: {
    width: drawerWidth,
  },
}));

const Channels = ({children}) => {
  const classes = useStyles();
  const [state, setState] = React.useState(false);

  const toggleChannels = () => {
    if (state === true){
      setState(false);
    }
    else {
      setState(true);
    }
  };

  return (
    <div className={classes.root}>
      <CssBaseline />
      <React.Fragment key={'left'}>
        <Drawer className={classes.drawer}
                variant={"persistent"} anchor={'left'} open={state}
                classes={{
                  paper: classes.drawerPaper,
                }}>
          <div>
            <List>
              {['Test1', 'Test2'].map((text) => (
                <ListItem button key={text} className={classes.drawerPaper}>
                  <ListItemIcon><MessageIcon /></ListItemIcon>
                  <ListItemText primary={text} />
                </ListItem>
              ))}
            </List>
          </div>
        </Drawer>
        <main
          className={clsx(classes.content, {
            [classes.contentShift]: state,
          })}
        >
          <Button onClick={toggleChannels}>Open</Button>
          {children}
        </main>
      </React.Fragment>
    </div>
  )
}

export default Channels;