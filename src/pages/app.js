import React from "react"

import SEO from "../components/seo"
import Message from "../components/message"
import Channels from "../components/channels"

const SecondPage = () => (
  <Channels>
      <SEO title="Home" />
      <Message author={"hello"} time={"hello"} message={"Good morning!"}/>
      <Message author={"hello"} time={"hello"} message={"Good morning!"}/>
      <Message author={"hello"} time={"hello"} message={"Good morning!"}/>
  </Channels>
)

export default SecondPage
