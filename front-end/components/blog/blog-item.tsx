import { TOKEN } from "@/config";
import { GetServerSideProps } from "next";
import Link from "next/link";


type Items = {
    description: [{
        plain_text: string;
    }]
}

interface Props {
    repo?: any;
}
// export const getServerSideProps: GetServerSideProps<{
//     //   repo: Items
//     }> = async () => {
//     const options = {
//         method: "get",
//         headers: {
//             // Accept: 'application/json',
//             'Notion-Version': '2022-06-28',
//             'Content-Type': 'application/json',
//             Authorization: `Bearer ${TOKEN}`
//         },
//         // body: JSON.stringify({
//         //     sorts: [
//         //         {
//         //             "property": "Name",
//         //             "direction": "ascending"
//         //         }
//         //     ],
//         //     page_size: 100
//         // })
//     };
//     const res = await fetch(`https://api.notion.com/v1/databases/${id}`, options);
//     const repo = await res.json();

//     console.log(repo)

//     return { props: { repo } }
// }

export default function BlogItem(){
    
    return (
        
        <div className="p-4 md:w-1/3">
            <div className="flex rounded-lg h-full bg-gray-100 p-8 flex-col">
            <div className="flex items-center mb-3">
                <div className="w-8 h-8 mr-3 inline-flex items-center justify-center rounded-full bg-indigo-500 text-white flex-shrink-0">
                <svg fill="none" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" className="w-5 h-5" viewBox="0 0 24 24">
                    <path d="M22 12h-4l-3 9L9 3l-3 9H2"></path>
                </svg>
                </div>
                <h2 className="text-gray-900 text-lg title-font font-medium">Shooting Stars</h2>
            </div>
            <div className="flex-grow">
                <p className="leading-relaxed text-base"></p>
                <a className="mt-3 text-indigo-500 inline-flex items-center">Learn More
                <svg fill="none" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" className="w-4 h-4 ml-2" viewBox="0 0 24 24">
                    <path d="M5 12h14M12 5l7 7-7 7"></path>
                </svg>
                </a>
            </div>
            </div>
        </div>
    )
}