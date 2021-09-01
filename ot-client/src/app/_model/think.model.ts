import { Article } from "./article.model";

export class Think extends Article {
    constructor(
        public documentLink?: string,
        public journal?: string,
    ) {
        super();
    }
}