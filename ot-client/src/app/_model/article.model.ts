export class Article {
    constructor(
        public theme?: string,
        public domain?: string,
        public description?: string,
        public abstracts?: string,
        public isPublished?: boolean,
        public publishedAt?: string,
    ) { }
}