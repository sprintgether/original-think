import { Article } from "./article.model";

export class Thesis extends Article {
    constructor(
        public documentLink?: string,
        public studyLevel?: string,
    ) {
        super();
    }
}