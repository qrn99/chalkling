import React from 'react';
import Grid from '@material-ui/core/Grid';

const Message = ({author, time, message}) => (
  <Grid item xs={12}>
    <p><b>{author} </b>{time}</p>
    <p>{message}</p>
  </Grid>
)

export default Message;