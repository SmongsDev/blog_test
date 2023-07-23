/* eslint-disable react/jsx-key */
import AnnualBlog from "@/components/blog/annual-blog";
import BlogItem from "@/components/blog/blog-item";
import Layout from "@/components/layout";
import { NODE_MAP_ID, TOKEN } from "@/config";
import { GetServerSideProps, InferGetServerSidePropsType } from "next";
import Head from "next/head";

type Repo = {

}

export const getServerSideProps: GetServerSideProps<{
      repo: Repo
    }> = async () => {
        const options = {
            method: "get",
            headers: {
                // Accept: 'application/json',
                'Notion-Version': '2022-06-28',
                'Content-Type': 'application/json',
                Authorization: `Bearer ${TOKEN}`
            },
            // body: JSON.stringify({
            //     sorts: [
            //         {
            //             "property": "Name",
            //             "direction": "ascending"
            //         }
            //     ],
            //     page_size: 100
            // })
        };
    
        const res = await fetch(`https://api.notion.com/v1/pages/${NODE_MAP_ID}`, options);
        
        const repo = await res.json();

        // console.log(repo);
    
        return { props: { repo } }
    }
    

export default function Blog(){

    return (
        <>
            <Layout>
                <Head>
                    <title>Blog - SMONGS Developer</title>
                    <meta name="description" content="오늘도 빡코딩!" />
                    <link rel="icon" href="/favicon.ico" />
                </Head>
                <section className="text-gray-600 body-font">
                    <div className="container px-5 py-24 mx-auto">
                        <div className="flex flex-col text-center w-full mb-20">
                            {/* <h2 className="text-xs text-indigo-500 tracking-widest font-medium title-font mb-1">ROOF PARTY POLAROID</h2> */}
                            <h1 className="sm:text-3xl text-2xl font-medium title-font text-gray-900">My Life Node Map</h1>
                            <div className="flex mt-3 justify-center">
                                <div className="w-64 h-1 rounded-full bg-indigo-500 inline-flex"></div>
                            </div>
                        </div>
                        <div className="flex flex-wrap -m-4">
                            {/* <BlogItem id={LIFE_GOAL_ID}/> */}
                            <AnnualBlog />
                            {/* <BlogItem id={TODO_ID}/> */}
                        </div>
                    </div>
                </section>
            </Layout>
        </>
    )
}