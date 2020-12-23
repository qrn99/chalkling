import React from "react"
import { Link } from "gatsby"
import '../styles/blog.css'

// Source: https://www.gatsbyjs.com/docs/adding-a-list-of-markdown-blog-posts/

const PostLink = ({ post }) => (
  <div>
    <Link to={post.frontmatter.slug}>
      <h1 id={'postListTitle'}>{post.frontmatter.title}</h1>
    </Link>
    Written by {post.frontmatter.author} on {post.frontmatter.date}
  </div>
)

export default PostLink