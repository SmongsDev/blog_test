import Head from "next/head";
import Layout from "@/components/layout";

export default function home (){
    return (
        <Layout>
            <div>
                <Head>
                    <title>SMONGS BLOG</title>
                    <meta name="description" content="오늘도 빡코딩!" />
                    <link rel="icon" href="/favicon.ico" />
                </Head>
                <h1 className="text-3xl font-bold underline">
                    홈 입니다.
                </h1>
            </div>
        </ Layout>
    )
}