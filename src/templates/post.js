import React from "react"
import { graphql } from "gatsby"

import Layout from "../components/layout"
import SEO from "../components/seo"
import "../styles/blog.css"

// Source: https://www.gatsbyjs.com/docs/how-to/routing/adding-markdown-pages/
export default function Post({data}) {
  const { markdownRemark } = data
  const { frontmatter, html } = markdownRemark
  return (
    <Layout>
      <SEO title={frontmatter.title} />
      <div id={'post'}>
        <h1>{frontmatter.title}</h1>
        Author: {frontmatter.author} <span style={{marginLeft: '5px', marginRight: '5px'}}>|</span> Date: {frontmatter.date}
        <div
          className="blog-post-content"
          dangerouslySetInnerHTML={{ __html: html }}
        />
      </div>
    </Layout>
  )
}

export const pageQuery = graphql`
  query($slug: String!) {
    markdownRemark(frontmatter: { slug: { eq: $slug } }) {
      html
      frontmatter {
        date(formatString: "YYYY-MM-DD")
        slug
        title
        author
      }
    }
  }
`