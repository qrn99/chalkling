import React from "react"

import Layout from "../components/layout"
import SEO from "../components/seo"
import PostLink from "../components/post-link"
import "../styles/blog.css"

// Source: https://www.gatsbyjs.com/docs/adding-a-list-of-markdown-blog-posts/

const BlogPage = ({
                    data: {
                      allMarkdownRemark: { edges },
                    },
                  }) => {
  const Posts = edges
    .filter(edge => !!edge.node.frontmatter.date) // You can filter your posts based on some criteria
    .map(edge => <PostLink key={edge.node.id} post={edge.node} />)
  return (
    <Layout>
      <SEO title="Blog" />
      <div id={'post'}>
        {Posts}
      </div>
    </Layout>);
}
export default BlogPage

export const pageQuery = graphql`
  query {
    allMarkdownRemark(sort: { order: DESC, fields: [frontmatter___date] }) {
      edges {
        node {
          id
          excerpt(pruneLength: 250)
          frontmatter {
            date(formatString: "YYYY-MM-DD")
            slug
            title
            author
          }
        }
      }
    }
  }
`