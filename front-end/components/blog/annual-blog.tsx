import { ANNUAL_GOAL_ID, TOKEN } from "@/config";
import type { InferGetServerSidePropsType, GetServerSideProps } from 'next'

type Repo = {
    title: [{
        plain_text: string;
    }]
    description: [{
        plain_text: string;
    }]
}

export default function AnnualBlog({
    repo
}: InferGetServerSidePropsType<typeof getServerSideProps>){

    if (!repo) return <p>No profile data</p>
    
    const title = repo.title[0].plain_text;
    const text = repo.description[0].plain_text;
    
    return (
        
        <div className="p-4 md:w-1/3">
            <div className="flex rounded-lg h-full bg-gray-100 p-8 flex-col">
            <div className="flex items-center mb-3">
                <div className="w-8 h-8 mr-3 inline-flex items-center justify-center rounded-full bg-indigo-500 text-white flex-shrink-0">
                <svg fill="none" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" className="w-5 h-5" viewBox="0 0 24 24">
                    <path d="M22 12h-4l-3 9L9 3l-3 9H2"></path>
                </svg>
                </div>
                <h2 className="text-gray-900 text-lg title-font font-medium">{title}</h2>
            </div>
            <div className="flex-grow">
                <p className="leading-relaxed text-base">{text}</p>
                <a className="mt-3 text-indigo-500 inline-flex items-center">Learn More
                <svg fill="none" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" className="w-4 h-4 ml-2" viewBox="0 0 24 24">
                    <path d="M5 12h14M12 5l7 7-7 7"></path>
                </svg>
                </a>
            </div>
            </div>
        </div>
    );
}

export const getServerSideProps: GetServerSideProps<{
    repo: Repo
  }> = async () => {
    const options = {
            method: "get",
            headers: {
                'Notion-Version': '2022-06-28',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${TOKEN}`
            }
        };
    const res = await fetch(`https://api.notion.com/v1/databases/${ANNUAL_GOAL_ID}`, options)
    const repo = await res.json()

    console.log(repo);
    return { props: { repo } }
  }
