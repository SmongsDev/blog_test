import type { AppProps } from "next/app";
import { ThemeProvider } from 'next-themes'
import { BrowserRouter as  Router, Route, Routes, Link } from 'react-router-dom';

import "@/styles/globals.css";
import Home from "./index";
import Projects from "./projects";

const App = ({ Component, pageProps }: AppProps) => {

  return (
    // <Router>
    //   <nav>
    //     <Link to="/"> Home </Link>
    //     <Link to="/projects"> Project</Link>
    //   </nav>
    //   <Routes>
    //     <Route path='/' element={<Home />} />
    //     <Route path='/projects' element={<Projects />} />
    //     <Route path='/login' component={Login} />
    //     <Route path='/setting' component={Setting} />
    //     <Route path='/search/query=:word' component={Search} />
    //     <Route path='/search/query=' component={Search} />
    //     <Route path='/naver' component={NaverLogin} />
    //   </Routes>
    // </Router>
    <ThemeProvider attribute="class">
      <Component {...pageProps} />
    </ThemeProvider>
  );
};

export default App;