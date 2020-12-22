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
        <p>Date: {frontmatter.date}</p>
        <p>Written by {frontmatter.author}</p>
        <div
          className="blog-post-content"
          dangerouslySetInnerHTML={{ __html: html }}
        />
      </div>
    </Layout>
  )
}

// TODO: 3:34:48 PM: warning Using the global `graphql` tag is deprecated, and will not be supported in v3.
//  3:34:48 PM: Import it instead like:  import { graphql } from 'gatsby' in file:
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