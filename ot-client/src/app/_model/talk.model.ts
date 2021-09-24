import { Article } from "./article.model";

export class Talk extends Article {
    constructor(
        public documentLink?: string,
        public journal?: string,
    ) {
        super();
    }
}