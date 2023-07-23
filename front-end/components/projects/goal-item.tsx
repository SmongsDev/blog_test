import ProjectType from "@/interface/projectType.interface";
import Image from "next/image";

interface Mult{
    id: string;
    name: string;
    color: string;
}
interface P{
    key?: string | undefined;
    data: ProjectType;
}

export default function GoalItem({data}: P){
    const icon = data.icon.emoji;
    const title = data.properties.Name.title[0].plain_text;
    const categorys = data.properties.Category.multi_select;
    const status = data.properties.Status.select.name;
    const priority = data.properties.Priority.select.name;
    const imgSrc = data.cover.file?.url || data.cover.external.url;
    
    const categoryList = categorys.map((aCat: Mult) => <h1 className="tag-card bg-lime-200 dark:bg-sky-700" key={aCat.id}>{aCat.name}</h1>);

    return (
        <>
            <div className="xl:w-1/4 md:w-1/2 p-4">
                <div className="bg-gray-100 rounded-lg project-card">
                    <Image className="h-60 rounded-t-xl w-full object-cover object-center" width={500} height={500} src={imgSrc} alt="cover image" objectFit="none" quality={100} priority/>
                    
                    <div className="p-4 flex flex-col">
                        <h1 className="text-lg text-gray-900 font-bold title-font mt-1">{icon} {title}</h1>
                        {/* <h3 className="leading-relaxed mt-2 text-base text-gray-800 mb-1">{content}</h3> */}

                        <div className="flex items-start mt-2">
                            {categoryList}
                        </div>
                        <div className="flex items-start mt-2">
                            {priority == 'Low'
                                ? <h1 className="tag-card bg-yellow-200 dark:text-slate-700">{priority}</h1>
                                : (priority == 'Medium'
                                    ? <h1 className="tag-card bg-orange-400 dark:text-slate-700">{priority}</h1>
                                    : <h1 className="tag-card bg-fuchsia-300 dark:text-slate-700">{priority}</h1>
                                )
                            }
                            
                        </div>

                    </div>
                </div>
            </div>
        </>
    )
}