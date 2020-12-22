import React from "react"
import Button from '@material-ui/core/Button'

import Layout from "../components/layout"
import SEO from "../components/seo"
import '../styles/frontpage.css'

export default function IndexPage() {

  return (
    <Layout>
      <SEO title="Home" />
      <div id={'front'}>
        <h1>Welcome to Chalkling</h1>
        <Button color="inherit" size={"large"} variant={"outlined"} disabled>Open Chalkling</Button>
      </div>
    </Layout>
  );
}
