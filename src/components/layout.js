/**
 * Layout component that queries for data
 * with Gatsby's useStaticQuery component
 *
 * See: https://www.gatsbyjs.com/docs/use-static-query/
 */

import React from "react"

import "../styles/layout.css"
import Footer from "./footer"
import Navbar from "./navbar"

// TODO: Fix layout + navbar
const Layout = ({ children }) => {

  return (
    <div id={"page"}>
      <div id={"content"}>
        <Navbar />
          <main>{children}</main>
      </div>
      <Footer />
    </div>
  )
}

export default Layout
