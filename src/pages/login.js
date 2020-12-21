import React from "react"

import Layout from "../components/layout"
import SEO from "../components/seo"
import LoginFields from "../components/loginfields";

const LoginPage = () => (
  <Layout>
    <SEO title="Home" />
    <LoginFields />
  </Layout>
)

export default LoginPage