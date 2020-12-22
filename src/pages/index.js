import React from "react"
import Button from '@material-ui/core/Button';

import Layout from "../components/layout"
import SEO from "../components/seo"
import { makeStyles } from "@material-ui/core/styles"

const useStyles = makeStyles((theme) => ({
  front: {
    paddingTop: '25vh',
    paddingBottom: '25vh',
    },
}));

export default function IndexPage() {
  const classes = useStyles();

  return (
    <Layout>
      <SEO title="Home" />
      <div className={classes.front}>
        <h1>Welcome to Chalkling</h1>
        <Button color="inherit" size={"large"} variant={"outlined"} disabled>Open Chalkling</Button>
      </div>
    </Layout>
  );
}
