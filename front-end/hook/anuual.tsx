import { ANNUAL_GOAL_ID, TOKEN } from '@/config'
import { useState, useEffect } from 'react'

interface Items{
    repo:{
        title: [{
            plain_text: string;
        }]
        description: [{
            plain_text: string;
        }]
    }
}

function AnnualData() {
    const [data, setData] = useState(null);
    const [isLoading, setLoading] = useState(false);

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
   
    useEffect(() => {
      setLoading(true)
      fetch(`https://api.notion.com/v1/databases/${ANNUAL_GOAL_ID}`, options)
        .then((res) => res.json())
        .then((data) => {
          setData(data)
          setLoading(false)
        })
    }, [])
   
    if (isLoading) return <p>Loading...</p>
    if (!data) return <p>No profile data</p>

    return data;
}  

export default AnnualData;