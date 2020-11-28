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

// source: https://material-ui.com/components/drawers/

const useStyles = makeStyles({
  list: {
    width: 250,
  }
});

const Channels = () => {
  const classes = useStyles();
  const [state, setState] = React.useState({
    left: false,
  });

  const toggleChannels = (anchor, open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }
    setState({ ...state, [anchor]: open });
  }

  const list = (anchor) => (
    <div className={clsx(classes.list)}
         role="presentation"
         onClick={toggleChannels(anchor, false)}
         onKeyDown={toggleChannels(anchor, false)}>
      <List>
        {['Test1', 'Test2'].map((text) => (
          <ListItem button key={text}>
            <ListItemIcon><MessageIcon /></ListItemIcon>
            <ListItemText primary={text} />
          </ListItem>
        ))}
      </List>

    </div>
  )

  return (
    <div>
      <React.Fragment key={'left'}>
        <Button onClick={toggleChannels('left', true)}>Open</Button>
        <Drawer anchor={'left'} open={state['left']} onClose={toggleChannels('left', false)}>
          {list('left')}
        </Drawer>
      </React.Fragment>
    </div>
  )
}

export default Channels;