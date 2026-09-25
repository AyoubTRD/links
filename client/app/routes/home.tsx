import type { Route } from "./+types/home";
import { Welcome } from "../welcome/welcome";

export function meta({ }: Route.MetaArgs): Route.MetaDescriptors {
    return [
        { title: "Ayoub Taouarda - Links" },
        { name: "description", content: "Quicklinks from Ayoub Taouarda" },
    ];
}

export default function Home() {
    return <Welcome />;
}
