import React from "react"
import { Link } from "gatsby"

// Source: https://www.gatsbyjs.com/docs/adding-a-list-of-markdown-blog-posts/

const PostLink = ({ post }) => (
  <h3>
    <Link to={post.frontmatter.slug}>
      {post.frontmatter.title} by {post.frontmatter.author}
    </Link>
  </h3>
)

export default PostLink