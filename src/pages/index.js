import React from "react"
import Layout from "../components/layout"
import SEO from "../components/seo"
import Message from "../components/message"
import Channels from "../components/channels"

const IndexPage = () => (
  <Channels>
  <Layout>
    <SEO title="Home" />
        <Message author={"hello"} time={"hello"} message={"Good morning!"}/>
        <Message author={"hello"} time={"hello"} message={"Good morning!"}/>
        <Message author={"hello"} time={"hello"} message={"Good morning!"}/>
  </Layout>
  </Channels>
)

export default IndexPage
