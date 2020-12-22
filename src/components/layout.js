/**
 * Layout component that queries for data
 * with Gatsby's useStaticQuery component
 *
 * See: https://www.gatsbyjs.com/docs/use-static-query/
 */

import React from "react"
import PropTypes from "prop-types"

import "../styles/layout.css"
import Footer from "./footer"
import Navbar from "./navbar"

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

Layout.propTypes = {
  children: PropTypes.node.isRequired,
}

export default Layout
