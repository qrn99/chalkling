import React from "react"
import Button from '@material-ui/core/Button'
import FadeIn from 'react-fade-in'

import Layout from "../components/layout"
import SEO from "../components/seo"
import '../styles/frontpage.css'

export default function IndexPage() {

  return (
    <Layout>
      <SEO title="Home" />
      <div id={'front'}>
        <FadeIn delay={100}>
          <h1>Welcome to Chalkling</h1>
          <Button color="inherit" size={"large"} variant={"outlined"} disabled>Open Chalkling</Button>
        </FadeIn>
      </div>
    </Layout>
  );
}
