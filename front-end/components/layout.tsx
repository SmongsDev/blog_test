import Footer from "./footer";
import Header from "./header";

interface IProps {
    children: any;
}

export default function Layout({ children }: IProps){
    return (
        <>
            <Header />
            <h1>레이아웃</h1>
            <div>{children}</div>
            <Footer />
        </>
    )
}