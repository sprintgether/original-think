import { Article } from "./article.model";

export class Studytrip extends Article {
    constructor(
        public locality?: string,
        public mentorName?: string,
        public mentorEmail?: string,
    ) {
        super();
    }
}