import React from "react"
import Layout from "../components/layout"
import SEO from "../components/seo"
import Message from "../components/message"

const IndexPage = () => (
  <Layout>
    <SEO title="Home" />
    <Message author={"hello"} time={"hello"} message={"Good morning!"}/>
  </Layout>
)

export default IndexPage
